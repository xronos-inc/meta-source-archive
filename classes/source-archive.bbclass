# SPDX-FileCopyrightText: (c) 2025 Xronos Inc.
# SPDX-License-Identifier: BSD-3-Clause

# rootfs post-processing function that copies archived sourced packages into the root fs
do_source_archive() {
    # validate source directory existence
    if [ ! -d "${DEPLOY_DIR}/sources" ]; then
        echo "ERROR: Source directory does not exist: ${DEPLOY_DIR}/sources"
        exit 1
    fi

    # validate source directory is not empty
    if [ -z "$(ls -A ${DEPLOY_DIR}/sources 2>/dev/null)" ]; then
        echo "ERROR: Source files not found."
        exit 2
    fi

    # create target directory in root filesystem
    install -d "${IMAGE_ROOTFS}/usr/src/yocto-sources"
    echo "Copying source files into final image at ${IMAGE_ROOTFS}/usr/src/yocto-sources..."

    mkdir -p ${IMAGE_ROOTFS}/usr/src/yocto-sources/
    cp --recursive \
        --force \
        --update \
        --no-preserve=ownership,mode \
        ${DEPLOY_DIR}/sources/* \
        ${IMAGE_ROOTFS}/usr/src/yocto-sources/
}
