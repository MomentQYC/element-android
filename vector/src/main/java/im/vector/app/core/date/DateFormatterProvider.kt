/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.core.date

import org.threeten.bp.format.DateTimeFormatter

interface DateFormatterProvider {

    val dateWithMonthFormatter: DateTimeFormatter

    val dateWithYearFormatter: DateTimeFormatter
}