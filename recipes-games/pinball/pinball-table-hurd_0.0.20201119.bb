DESCRIPTION = "HURD Pinball table for Emilia Pinball Simulator"
HOMEPAGE = "https://github.com/rzr/pinball-table-hurd"
SECTION = "games"
LICENSE = "GPLv2 & LAL-1.3 & CC-BY-SA-3.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://debian/copyright;md5=894ad8c75ec3bbd77c06d637d85dbb63 \
"

SRC_URI = "git://github.com/rzr/pinball-table-hurd.git;protocol=https"
SRCREV = "0.0.20201119"

S = "${WORKDIR}/git"

DEPENDS += "libsdl \
    pinball \
"

inherit pkgconfig autotools-brokensep

PACKAGECONFIG ??= "sdl"
PACKAGECONFIG[sdl] = "--enable-sdl,--disable-sdl,libsdl"

PACKAGES = "${PN}"
PACKAGES += "${PN}-dev"
PACKAGES += "${PN}-staticdev"
PACKAGES += "${PN}-dbg"
PACKAGES += "${PN}-data"

REMOVE_LIBTOOL_LA = "0"

do_configure:prepend() {
    cd ${S}
    ./bootstrap
}

FILES:${PN}-dev = "\
${includedir}/${PN}/* \
"

FILES:${PN}-staticdev = "\
${libdir}/pinball/lib*.a \
"

FILES:${PN}-dbg = "\
${libdir}/pinball/.debug/libModule*.so* \
"

FILES:${PN} = "\
${libdir}/pinball/libModule*.so* \
${libdir}/pinball/libModule*.la \
"

FILES:${PN}-data = "\
${datadir}/pinball/*/* \
${localstatedir}/games/pinball/*/highscores \
"

RDEPENDS:${PN} += " pinball"
RDEPENDS:${PN} += " ${PN}-data"
INSANE_SKIP:${PN} = "dev-so"
