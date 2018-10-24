/*
 * MIT License
 *
 * Copyright (c) 2018 g4s8
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.g4s8.teletakes.tk;

import com.g4s8.teletakes.rs.TmResponse;
import java.io.IOException;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Take to process single Telegram request.
 *
 * @since 1.0
 */
public interface TmTake {

    /**
     * Process request.
     *
     * @param upd Request
     * @return Response
     * @throws IOException If fails
     */
    TmResponse act(Update upd) throws IOException;

    /**
     * Take decorator.
     */
    abstract class Wrap implements TmTake {

        /**
         * Origin take.
         */
        private final TmTake origin;

        /**
         * Wrap take.
         *
         * @param origin Take to wrap
         */
        protected Wrap(final TmTake origin) {
            this.origin = origin;
        }

        @Override
        public final TmResponse act(final Update upd) throws IOException {
            return this.origin.act(upd);
        }
    }
}
