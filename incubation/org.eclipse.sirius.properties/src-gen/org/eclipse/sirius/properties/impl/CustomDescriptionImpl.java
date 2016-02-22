/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Custom Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl#getCustomExpressions
 * <em>Custom Expressions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl#getCustomOperations
 * <em>Custom Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomDescriptionImpl extends WidgetDescriptionImpl implements CustomDescription {
    /**
     * The cached value of the '{@link #getCustomExpressions()
     * <em>Custom Expressions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCustomExpressions()
     * @generated
     * @ordered
     */
    protected EList<CustomExpression> customExpressions;

    /**
     * The cached value of the '{@link #getCustomOperations()
     * <em>Custom Operations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCustomOperations()
     * @generated
     * @ordered
     */
    protected EList<CustomOperation> customOperations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CustomDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CUSTOM_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CustomExpression> getCustomExpressions() {
        if (customExpressions == null) {
            customExpressions = new EObjectContainmentEList<CustomExpression>(CustomExpression.class, this, PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        }
        return customExpressions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CustomOperation> getCustomOperations() {
        if (customOperations == null) {
            customOperations = new EObjectContainmentEList<CustomOperation>(CustomOperation.class, this, PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        }
        return customOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return ((InternalEList<?>) getCustomExpressions()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return ((InternalEList<?>) getCustomOperations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return getCustomExpressions();
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return getCustomOperations();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            getCustomExpressions().clear();
            getCustomExpressions().addAll((Collection<? extends CustomExpression>) newValue);
            return;
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            getCustomOperations().clear();
            getCustomOperations().addAll((Collection<? extends CustomOperation>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            getCustomExpressions().clear();
            return;
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            getCustomOperations().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return customExpressions != null && !customExpressions.isEmpty();
        case PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return customOperations != null && !customOperations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // CustomDescriptionImpl
