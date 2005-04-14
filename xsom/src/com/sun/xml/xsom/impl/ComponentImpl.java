/*
 * @(#)$Id: ComponentImpl.java,v 1.1 2005-04-14 22:06:24 kohsuke Exp $
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.xml.xsom.impl;

import com.sun.xml.xsom.XSAnnotation;
import com.sun.xml.xsom.XSComponent;
import com.sun.xml.xsom.XSSchema;
import org.xml.sax.Locator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ComponentImpl implements XSComponent
{
    protected ComponentImpl( SchemaImpl _owner, AnnotationImpl _annon, Locator _loc, ForeignAttributesImpl fa ) {
        this.ownerSchema = _owner;
        this.annotation = _annon;
        this.locator = _loc;
        this.foreignAttributes = fa;
    }

    protected final SchemaImpl ownerSchema;
    public final XSSchema getOwnerSchema() { return ownerSchema; }
    
    private final AnnotationImpl annotation;
    public final XSAnnotation getAnnotation() { return annotation; }
    
    private final Locator locator;
    public final Locator getLocator() { return locator; }

    /**
     * Either {@link ForeignAttributesImpl} or {@link List}.
     *
     * Initially it's {@link ForeignAttributesImpl}, but it's lazily turned into
     * a list when necessary.
     */
    private Object foreignAttributes;

    public List getForeignAttributes() {
        Object t = foreignAttributes;

        if(t==null)
            return Collections.EMPTY_LIST;

        if(t instanceof List)
            return (List)t;

        t = foreignAttributes = convertToList((ForeignAttributesImpl)t);
        return (List)t;
    }

    public String getForeignAttribute(String nsUri, String localName) {
        for( ForeignAttributesImpl fa : (List<ForeignAttributesImpl>)getForeignAttributes() ) {
            String v = fa.getValue(nsUri,localName);
            if(v!=null) return v;
        }
        return null;
    }

    private List convertToList(ForeignAttributesImpl fa) {
        List lst = new ArrayList();
        while(fa!=null) {
            lst.add(fa);
            fa = fa.next;
        }
        return Collections.unmodifiableList(lst);
    }
}
