/*
 * @(#)$Id: XSWildcardFunction.java,v 1.1 2005-04-14 22:06:40 kohsuke Exp $
 *
 * Copyright 2002 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.xml.xsom.visitor;

import com.sun.xml.xsom.XSWildcard;

/**
 * Visits three kinds of {@link XSWildcard}.
 * 
 * @author
 *     Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 */
public interface XSWildcardFunction<T> {
    T any( XSWildcard.Any wc );
    T other( XSWildcard.Other wc );
    T union( XSWildcard.Union wc );
}
