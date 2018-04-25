/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to display the description of a {@link CustomLayoutConfiguration}.
 */
public class CustomLayoutConfigurationDescriptionPropertySection extends AbstractViewpointPropertySection {

    /**
     * The description widget.
     */
    protected CLabel description;

    /**
     * The high level composite containing the widgets.
     */
    private Composite composite;

    /**
     * The label for the description.
     */
    private CLabel nameLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        String descriptionString = "";
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) eObject;
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        CustomLayoutAlgorithm customLayoutAlgorithm = layoutProviderRegistry.get(layout.getId());
        if (customLayoutAlgorithm != null) {
            descriptionString = customLayoutAlgorithm.getDescription() == null ? "" : customLayoutAlgorithm.getDescription();
        }

        description = getWidgetFactory().createCLabel(composite, descriptionString, SWT.MULTI | SWT.WRAP);
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        data.width = 100;
        description.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, customLayoutAlgorithm.getLabel());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(description, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(description, 0, SWT.TOP);
        nameLabel.setLayoutData(data);

    }

    @Override
    public void dispose() {
        description.dispose();
        nameLabel.dispose();
        super.dispose();
    }

    @Override
    public EAttribute getFeature() {
        return null;
    }

    @Override
    protected void makeReadonly() {
    }

    @Override
    protected void makeWrittable() {
    }

}
