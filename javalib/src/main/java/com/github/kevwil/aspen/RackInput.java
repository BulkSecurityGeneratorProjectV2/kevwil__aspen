/*
 * Copyright 2007-2009 Sun Microsystems, Inc.
 * This source code is available under the MIT license.
 * See the file LICENSE.txt for details.
 */


package com.github.kevwil.aspen;

import org.jruby.runtime.*;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * Specification for Rack input, translated to a Java interface.
 * @author nicksieger
 * @author kevwil
 * @since Nov 20, 2009
 */
public interface RackInput
{
    /**
     * gets must be called without arguments and return a string, or nil on EOF.
     * @param context ruby context
     * @return nil
     */
    IRubyObject gets( ThreadContext context );

    /**
     * read behaves like IO#read. Its signature is read([length, [buffer]]). If given,
     * length must be an non-negative Integer (>= 0) or nil, and buffer must be a
     * String and may not be nil. If length is given and not nil, then this method
     * reads at most length bytes from the input stream. If length is not given or
     * nil, then this method reads all data until EOF. When EOF is reached, this
     * method returns nil if length is given and not nil, or "" if length is not
     * given or is nil. If buffer is given, then the read data will be placed into
     * buffer instead of a newly created String object.
     * @param context ruby context
     * @param args length and buffer, both optional
     * @return nil
     */
    IRubyObject read( ThreadContext context, IRubyObject[] args );

    /**
     * each must be called without arguments and only yield Strings.
     * @param context ruby context
     * @param block lambda te execute on each return
     * @return nil
     */
    public IRubyObject each( ThreadContext context, Block block );

    /**
     * rewind must be called without arguments. It rewinds the input stream back
     * to the beginning. It must not raise Errno::ESPIPE: that is, it may not be
     * a pipe or a socket. Therefore, handler developers must buffer the input
     * data into some rewindable object if the underlying input stream is not rewindable.
     * @param context ruby context
     * @return nil
     */
    public IRubyObject rewind( ThreadContext context );

    /**
     * Close the input. Exposed only to the Java side because the Rack spec says
     * that application code must not call close, so we don't expose a close method to Ruby.
     */
    public void close();
}
