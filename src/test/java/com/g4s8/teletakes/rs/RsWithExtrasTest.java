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
package com.g4s8.teletakes.rs;

import com.jcabi.matchers.XhtmlMatchers;
import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link RsWithExtras}.
 *
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class RsWithExtrasTest {

    @Test
    @DisplayName("RsWithExtras can render XML")
    public void renderXml() throws Exception {
        final String key = "foo";
        final String val = "bar";
        MatcherAssert.assertThat(
            new RsWithExtras(
                Collections.singletonMap(key, val)
            ).xml(),
            XhtmlMatchers.hasXPaths(
                String.format(
                    "/response/extras/item[@key = '%s' and text() = '%s']",
                    key, val
                )
            )
        );
    }

    @Test
    @DisplayName("RsWithExtras must override extras with same key")
    public void overridesSameKeyExtras() throws Exception {
        final String key = "baz";
        final String val = "expected";
        MatcherAssert.assertThat(
            new RsWithExtras(
                new RsWithExtras(
                    key, "ignore"
                ),
                key, val
            ).xml(),
            XhtmlMatchers.hasXPaths(
                String.format(
                    "/response/extras/item[@key = '%s' and text() ='%s']",
                    key, val
                ),
                "/response/extras[count(item) = 1]"
            )
        );
    }
}
