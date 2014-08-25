/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.Set;

import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewer;

/**
 * A {@link Runnable} to update a {@link DTreeViewer} from the
 * {@link TreeUIUpdater}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeUIUpdaterRunnable implements Runnable {

    private DTreeViewer dTreeViewer;

    private Set<Object> toRefreshInViewer;

    private Object[] objectsToUpdateInViewer;

    private Set<DTreeItem> toExpand;

    private Set<DTreeItem> toCollapse;

    /**
     * Default constructor.
     * 
     * @param dTreeViewer
     *            the {@link DTreeViewer} to update
     * @param toRefreshInViewer
     *            the elements to refresh
     * @param objectsToUpdateInViewer
     *            the elements to update
     * @param toExpand
     *            the elements to expand
     * @param toCollapse
     *            the elements to collapse
     */
    public TreeUIUpdaterRunnable(DTreeViewer dTreeViewer, Set<Object> toRefreshInViewer, Object[] objectsToUpdateInViewer, Set<DTreeItem> toExpand, Set<DTreeItem> toCollapse) {
        this.dTreeViewer = dTreeViewer;
        this.toRefreshInViewer = toRefreshInViewer;
        this.objectsToUpdateInViewer = objectsToUpdateInViewer;
        this.toExpand = toExpand;
        this.toCollapse = toCollapse;
    }

    @Override
    public void run() {
        if (dTreeViewer != null && dTreeViewer.getControl() != null && !dTreeViewer.getControl().isDisposed()) {
            for (Object itemtoExpand : toExpand) {
                dTreeViewer.setExpandedState(itemtoExpand, true);
            }
            for (Object itemToCollapse : toCollapse) {
                dTreeViewer.setExpandedState(itemToCollapse, false);
            }
            for (Object itemToRefresh : toRefreshInViewer) {
                dTreeViewer.refresh(itemToRefresh, true);
            }
            /*
             * no need to update objects which got refresh already...
             */
            dTreeViewer.update(objectsToUpdateInViewer, null);
        }
    }

}
