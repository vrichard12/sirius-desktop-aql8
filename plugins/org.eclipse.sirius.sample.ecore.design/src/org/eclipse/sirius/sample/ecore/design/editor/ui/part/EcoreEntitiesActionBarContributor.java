/*******************************************************************************
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.ecore.design.editor.ui.part;

import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.sample.ecore.design.editor.EcoreEntitiesReadOnlyEditor;

/**
 * Specific ActionBarContributor for {@link EcoreEntitiesReadOnlyEditor} instance to avoid potential leaks on actions
 * for action bar of this editor.
 * 
 * @author Laurent Redor
 */
public class EcoreEntitiesActionBarContributor extends SiriusDiagramActionBarContributor {
    @Override
    protected Class<?> getEditorClass() {
        return EcoreEntitiesReadOnlyEditor.class;
    }
}
