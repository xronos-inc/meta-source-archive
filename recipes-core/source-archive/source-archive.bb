# SPDX-FileCopyrightText: (c) 2025 Xronos Inc.
# SPDX-License-Identifier: BSD-3-Clause

SUMMARY = "Package source code and licenses with an image."
DESCRIPTION = "Archive all source code and licenses from recipes into the image for distribution."
HOMEPAGE = "https://github.com/xronos-inc/meta-source-archive"

COMPANY = "Xronos Inc."
COPYRIGHT = "Copyright (c) 2025 Xronos Inc. All rights reserved."
AUTHOR = "Jeff C. Jensen <11233838+elgeeko1@users.noreply.github.com>"

SRC_URI = "git://github.com/xronos-inc/meta-source-archive;protocol=https;branch=main"
# 1.0.0-scarthgap
SRCREV = "1c881845fa99b96c6e184838d97424ed6522848a"
PV = "1.0.0-scarthgap"
S = "${WORKDIR}/git"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=36b0172a5a311813149b778f90d54880"
SRC_URI:append = " file://LICENSE"

# exclude this package from archiving
INHERIT += " create-spdx license"
INHERIT:remove = " archiver"

# force package install by touching a file, and (redundantly) allowing an empty package
do_install() {
    install -d ${D}/usr/share/${PN}
    touch ${D}/usr/share/${PN}/installed
}
ALLOW_EMPTY_${PN} = "1"
FILES:${PN} += "/usr/share/${PN}/installed"
