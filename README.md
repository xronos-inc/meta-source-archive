# meta-source-archive

## 📌 Overview

The Yocto layer `meta-source-archive` provides:

- Software source archiving for Yocto builds.
- SPDX compliance tracking for licensing.
- Reproducible build attestations.

## 📦 Included Features

- Archives all available source code and licenses during the Yocto build.
- Supports compliance with SPDX software bill of materials (SBOM).
- Integrates with `spdx`, `archiver`, and `reproducible_build`.

## 🚀 Installation

### 1️⃣ Clone the Repository

Clone this repository into your Yocto layers directory:

```shell
git clone https://github.com/xronos-inc/meta-source-archive.git layers/meta-source-archive
```

### 2️⃣ Add the Layer to `bblayers.conf`

Edit your `conf/bblayers.conf` file and add the following line:

```conf
BBLAYERS += " ${TOPDIR}/layers/meta-source-archive
```

### 3️⃣ Configure local.conf for SPDX and Archiving

Ensure your `conf/local.conf` file includes the following settings:

```conf
# Enable license tracking
INHERIT += " license"

# Define where license information will be stored
LICENSE_DIRECTORY = "${DEPLOY_DIR}/licenses"

# enable SPDX metadata tracking
INHERIT += " create-spdx"
SPDX_INCLUDE_SOURCES = "1"
SPDX_ARCHIVE_PACKAGED = "1"
SPDX_ARCHIVE_SOURCES = "1"

# include source packages in the final image
INHERIT += " archiver"
ARCHIVER_MODE[src] = "original"
COPY_LIC_MANIFEST = "1"
COPY_LIC_DIRS = "1"
LICENSE_CREATE_PACKAGE = "1"

INHERIT += " source-archive"
ROOTFS_POSTPROCESS_COMMAND += "do_source_archive;"

# include the meta-source-archive package
IMAGE_INSTALL:append = " source-archive"
```

This enables license tracking, source archiving, and reproducible builds, and adds a rootfs
post-process command that copies archived source into the root filesystem.

### 4️⃣ Build Your Image

Run the following command to build your Yocto image:

```shell
bitbake cassini-image-base
```

To only build the source archive package:

```shell
bitbake source-archive
```

### 5️⃣ Verify the Build Output

After the build completes, check the following directories:

- SPDX metadata: `ls build/tmp/deploy/spdx/`
- Source archives: `ls build/tmp/deploy/sources/`
- Installed files in the target rootfs: `build/tmp/work/<machine>/image-rootfs/usr/src/yocto-sources/`

## 📝 License

This layer is licensed under BSD-3-Clause. See [LICENSE](LICENSE) for details.
