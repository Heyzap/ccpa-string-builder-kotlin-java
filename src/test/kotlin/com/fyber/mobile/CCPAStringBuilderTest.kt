/** Copyright 2020 Fyber Monetization LTD

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
 */
package com.fyber.mobile

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests for all the valid CCPA Privacy string builder input options
 */
class CCPAStringBuilderTest {

    @Test
    fun `when all approved`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YYY")
    }

    @Test
    fun `when all unset`() {
        val privacyString = CCPAStringBuilder.create().build()
        assertEquals(privacyString, "1---")
    }

    @Test
    fun `when explicitOptOut is unset value at second char is -`() {
        val privacyString = CCPAStringBuilder.create()
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1-YY")
    }

    @Test
    fun `when explicitOptOut is approved value at second char Y`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YYY")
    }

    @Test
    fun `when explicitOptOut is decline value at second char N`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.NO)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1NYY")
    }

    @Test
    fun `when explicitOptOut is not applicable value at second char -`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.NOT_APPLICABLE)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1-YY")
    }

    @Test
    fun `when optOutSale is unset value at third char is -`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1Y-Y")
    }

    @Test
    fun `when optOutSale is approved value at third char Y`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YYY")
    }

    @Test
    fun `when optOutSale is decline value at third char N`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.NO)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YNY")
    }

    @Test
    fun `when optOutSale is not applicable value at third char -`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.NOT_APPLICABLE)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1Y-Y")
    }

    @Test
    fun `when limitedServiceProviderAgreement is unset value at last char is -`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YY-")
    }

    @Test
    fun `when limitedServiceProviderAgreement is approved value at second char Y`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()

        assertEquals(privacyString, "1YYY")
    }

    @Test
    fun `when limitedServiceProviderAgreement is decline value at last char N`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.NO)
            .build()

        assertEquals(privacyString, "1YYN")
    }

    @Test
    fun `when limitedServiceProviderAgreement is not applicable value at last char -`() {
        val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.NOT_APPLICABLE)
            .build()

        assertEquals(privacyString, "1YY-")
    }
}




