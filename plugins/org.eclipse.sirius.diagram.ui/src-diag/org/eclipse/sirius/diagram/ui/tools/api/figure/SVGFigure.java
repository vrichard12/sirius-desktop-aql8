/**
 * Copyright (c) 2008, 2015 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 *    Obeo - Adaptations.
 */
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.WeakHashMap;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SVGUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SimpleImageTranscoder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

//CHECKSTYLE:OFF
public class SVGFigure extends Figure {
    /**
     * The uri of the image to display when the file has not been found.
     */
    protected static final String IMAGE_NOT_FOUND_URI = MessageFormat.format("platform:/plugin/{0}/images/NotFound.svg", DiagramUIPlugin.getPlugin().getSymbolicName()); //$NON-NLS-1$

    /**
     * Key separator.
     */
    protected static final String SEPARATOR = "|"; //$NON-NLS-1$

    private String uri;

    private boolean failedToLoadDocument;

    private SimpleImageTranscoder transcoder;

    protected static WeakHashMap<String, Document> documentsMap = new WeakHashMap<String, Document>();

    public final String getURI() {
        return uri;
    }

    public final void setURI(String uri) {
        setURI(uri, true);
    }

    public void setURI(String uri, boolean loadOnDemand) {
        this.uri = uri;
        transcoder = null;
        failedToLoadDocument = false;
        if (loadOnDemand) {
            loadDocument();
        }
    }

    private void loadDocument() {
        transcoder = null;
        failedToLoadDocument = true;
        if (uri == null) {
            return;
        }

        String documentKey = getDocumentKey();
        Document document;
        if (documentsMap.containsKey(documentKey)) {
            document = documentsMap.get(documentKey);
        } else {
            document = createDocument();
            documentsMap.put(documentKey, document);
        }

        if (document != null) {
            transcoder = new SimpleImageTranscoder(document);
            failedToLoadDocument = false;
        }

    }

    private Document createDocument() {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        return createDocument(factory, false);
    }

    private Document createDocument(SAXSVGDocumentFactory factory, boolean forceClassLoader) {
        if (forceClassLoader) {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        }
        try {
            return factory.createDocument(uri);
        } catch (IOException e) {
            boolean saxParserNotFound = e.getMessage() != null && e.getMessage().contains("SAX2 driver class org.apache.xerces.parsers.SAXParser not found"); //$NON-NLS-1$
            if (!forceClassLoader && saxParserNotFound && Thread.currentThread().getContextClassLoader() == null) {
                return createDocument(factory, true);
            } else {
                DiagramPlugin.getDefault().logError(Messages.SVGFigure_loadError, e);
            }
        } finally {
            if (forceClassLoader) {
                Thread.currentThread().setContextClassLoader(null);
            }
        }
        return null;
    }

    protected final Document getDocument() {
        if (failedToLoadDocument) {
            return null;
        }
        if (transcoder == null) {
            loadDocument();
        }
        return transcoder == null ? null : transcoder.getDocument();
    }

    /**
     * The key used to store the document.
     * 
     * @return the key.
     */
    protected String getDocumentKey() {
        return uri;
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        Document document = getDocument();
        if (document == null) {
            return;
        }
        Image image = null;
        try {
            Rectangle r = getClientArea();
            transcoder.setCanvasSize(r.width, r.height);
            updateRenderingHints(graphics);
            BufferedImage awtImage = transcoder.getBufferedImage();
            if (awtImage != null) {
                image = SVGUtils.toSWT(Display.getCurrent(), awtImage);
                graphics.drawImage(image, r.x, r.y);
            }
        } finally {
            if (image != null) {
                image.dispose();
            }
        }
    }

    protected void updateRenderingHints(Graphics graphics) {
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getAntialias();
            } catch (Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
            }
            if (transcoder != null && transcoder.getRenderingHints().get(RenderingHints.KEY_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getTextAntialias();
            } catch (Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
            }
            if (transcoder != null && transcoder.getRenderingHints().get(RenderingHints.KEY_TEXT_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
    }

    /**
     * Should be called when SVG document has been changed. It will be
     * re-rendered and figure will be repainted.
     */
    public void contentChanged() {
        getDocument();
        if (transcoder != null) {
            transcoder.contentChanged();
        }
        repaint();
    }

    protected SimpleImageTranscoder getTranscoder() {
        return transcoder;
    }
    // CHECKSTYLE:ON
}
