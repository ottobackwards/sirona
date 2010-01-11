/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.monitoring.instrumentation.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.monitoring.Repository;
import org.apache.commons.monitoring.repositories.DefaultRepository;

/**
 *
 * @author <a href="mailto:nicolas@apache.org">Nicolas De Loof</a>
 */
public class ServletContextUtil
{
    /** key for Repository as ServletContext attribute */
    public static final String REPOSITORY_KEY = Repository.class.getName();

    public static synchronized Repository getRepository( ServletContext context )
        throws ServletException
    {
        Object attribute = context.getAttribute( REPOSITORY_KEY );
        if ( attribute != null )
        {
            if ( attribute instanceof Repository )
            {
                return (Repository) attribute;
            }
            else
            {
                throw new ServletException( "Attribute " + REPOSITORY_KEY + " in servletContext is not a Repository" );
            }
        }
        else
        {
            Repository repository = new DefaultRepository();
            context.setAttribute( REPOSITORY_KEY, repository );
            return repository;
        }
    }
}