/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

/**
 * Sequence cache helper.
 * 
 * @author nlepine
 * 
 */
public final class CacheHelper {

    private static boolean structuralCacheEnabled;

    private static boolean verticalRangeCacheEnabled;

    private static Map<AbstractFrame, Collection<Lifeline>> coverageCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<ISequenceEvent, Collection<ISequenceEvent>> subEventsCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<View, Range> viewToRangeCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<ISequenceEvent, Message> startCompoundMessageCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<ISequenceEvent, Message> endCompoundMessageCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<AbstractNodeEvent, ISequenceEvent> nodeEventToHierarchicalParentCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<ISequenceEvent, Option<Operand>> eventToParentOperandCache = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<ISequenceEvent, ISequenceEvent> eventToParentEventCache = new ConcurrentHashMap<>();

    /**
     * Avoid instantiation.
     */
    private CacheHelper() {
        // Do nothing.
    }

    /**
     * Return true if the structural caches are enabled.
     * 
     * @return true if the structural caches are enabled.
     */
    public static boolean isStructuralCacheEnabled() {
        return structuralCacheEnabled;
    }

    /**
     * Set if the structural cache cache is enabled.
     * 
     * @param enabled
     *            boolean
     */
    public static void setStructuralCacheEnabled(boolean enabled) {
        CacheHelper.structuralCacheEnabled = enabled;
    }

    /**
     * Return true if the vertical range cache is enabled.
     * 
     * @return true if the vertical range cache is enabled.
     */
    public static boolean isVerticalRangeCacheEnabled() {
        return verticalRangeCacheEnabled;
    }

    /**
     * Set if vertical range is enabled.
     * 
     * @param enabled
     *            boolean
     */
    public static void setVerticalRangeCacheEnabled(boolean enabled) {
        CacheHelper.verticalRangeCacheEnabled = enabled;
    }

    /**
     * Clear and disable all caches.
     */
    public static void clearCaches() {
        // Structural caches
        coverageCache.clear();
        startCompoundMessageCache.clear();
        endCompoundMessageCache.clear();
        nodeEventToHierarchicalParentCache.clear();
        clearRangeDependantCaches();
    }

    /**
     * Clear and disable range dependant caches.
     */
    public static void clearRangeDependantCaches() {
        // Range dependant cache
        subEventsCache.clear();
        eventToParentOperandCache.clear();
        eventToParentEventCache.clear();
        viewToRangeCache.clear();
    }

    /**
     * Get AbstractFrame.coveredCache.
     * 
     * @return Map<AbstractFrame, Collection<Lifeline>>
     */
    public static Map<AbstractFrame, Collection<Lifeline>> getCoverageCache() {
        return coverageCache;
    }

    /**
     * Get subEvents cache.
     * 
     * @return the subEventsCache
     */
    public static ConcurrentHashMap<ISequenceEvent, Collection<ISequenceEvent>> getSubEventsCache() {
        return subEventsCache;
    }

    /**
     * Get view to range cache.
     * 
     * @return the viewToRangecache
     */
    public static Map<View, Range> getViewToRangeCache() {
        return viewToRangeCache;
    }

    /**
     * Get start message cache.
     * 
     * @return the cache
     */
    public static ConcurrentHashMap<ISequenceEvent, Message> getStartCompoundMessageCache() {
        return startCompoundMessageCache;
    }

    /**
     * Get end message cache.
     * 
     * @return the cache
     */
    public static ConcurrentHashMap<ISequenceEvent, Message> getEndCompoundMessageCache() {
        return endCompoundMessageCache;
    }

    /**
     * Get hierarchical parent cache.
     * 
     * @return the cache
     */
    public static ConcurrentHashMap<AbstractNodeEvent, ISequenceEvent> getAbstractNodeEventToHierarchicalParentCache() {
        return nodeEventToHierarchicalParentCache;
    }

    /**
     * Get parent operand cache.
     * 
     * @return the cache
     */
    public static ConcurrentHashMap<ISequenceEvent, Option<Operand>> getEventToParentOperandCache() {
        return eventToParentOperandCache;
    }

    /**
     * Get parent event cache.
     * 
     * @return the cache
     */
    public static ConcurrentHashMap<ISequenceEvent, ISequenceEvent> getEventToParentEventCache() {
        return eventToParentEventCache;
    }

}
