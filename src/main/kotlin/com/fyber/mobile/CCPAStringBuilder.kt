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

/**
 * California Consumer Privacy Act String Builder Utility class<br/>
 * Builds a string as defined in the protocol for CCPA Privacy String Version 1.0 {@see https://iabtechlab.com/wp-content/uploads/2019/11/U.S.-Privacy-String-v1.0-IAB-Tech-Lab.pdf} <br/>
 * Example usage using Kotlin - <br/>
 *         val privacyString = CCPAStringBuilder.create()
 *                             .explicitOptOut(CCPAValidValue.YES)
 *                             .optOutSale(CCPAValidValue.YES)
 *                             .limitedServiceProviderAgreement(CCPAValidValue.YES)
 *                             .build()
 */
object CCPAStringBuilder {

    /**
     * The current version of the string, used in the first character in the built String
     */
    private const val CCPA_VERSION = "1"

    /**
     * Default constructor. creates a new instance.
     */
    @JvmStatic
    fun create(): CCPABuilder {
        return CCPABuilder()
    }


    /**
     * Utility class which uses fluent API to create the CCPA Privacy String.
     */
    class CCPABuilder {

        /**
         * The Second String component as defined in the specification -
         * Has explicit notice been provided as required by 1798.115(d) of the CCPA and
         * the opportunity to opt out of the sale of their data pursuant to 1798.120 and
         * 1798.135 of the CCPA
         */
        private var explicitOptOut: CCPAStringComponentValue = CCPAStringComponentValue.NOT_APPLICABLE

        /**
         * The Third String component as defined in the specification -
         * Has user opted-out of the sale of his or personal information pursuant to 1798.120 and 1798.135
         */
        private var optOutSale: CCPAStringComponentValue = CCPAStringComponentValue.NOT_APPLICABLE

        /**
         * The Fourth and last String component defined in the specification -
         * Publisher is a signatory to the IAB Limited Service Provider Agreement
         * (LSPA) and the publisher declares that the transaction is covered as a “Covered
         * Opt Out Transaction” or a “Non Opt Out Transaction” as those terms are defined
         * in the Agreement.
         */
        private var limitedServiceProviderAgreement: CCPAStringComponentValue = CCPAStringComponentValue.NOT_APPLICABLE

        /**
         * Sets [explicitOptOut]
         * @param explicitOptOut ccpa valid value enum - YES, NO, NOT_APPLICABLE
         * @return this for Fluent usage
         */
        fun explicitOptOut(explicitOptOut: CCPAStringComponentValue) = apply { this.explicitOptOut = explicitOptOut }

        /**
         * Sets [optOutSale]
         * @param optOutSale ccpa valid value enum
         * @return this for Fluent usage
         */
        fun optOutSale(optOutSale: CCPAStringComponentValue) = apply { this.optOutSale = optOutSale }

        /**
         * Sets [limitedServiceProviderAgreement]
         * @param limitedServiceProviderAgreement ccpa valid value enum
         * @return this for Fluent usage
         */
        fun limitedServiceProviderAgreement(limitedServiceProviderAgreement: CCPAStringComponentValue) = apply { this.limitedServiceProviderAgreement = limitedServiceProviderAgreement }

        /**
         * Constructs the CCPA Privacy String using the provided or the default inputs.
         * @return a String representing the values set in this builder, for example "1---" or "1YYY".
         */
        fun build(): String {
            return "$CCPA_VERSION${explicitOptOut.stringRepresentation}${optOutSale.stringRepresentation}${limitedServiceProviderAgreement.stringRepresentation}"
        }
    }
}