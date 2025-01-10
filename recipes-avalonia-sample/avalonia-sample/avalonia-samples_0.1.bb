inherit autotools
inherit mono
SRC_URI = "git://github.com/AvaloniaUI/Avalonia.Samples.git;protocol=https;branch=main"

SRCREV="2b547d92343081011dfa93e815acd1dd0b92bb31"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Entering bitbake build avalonia-samples    *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

DEPENDS += "dotnet"
RDEPENDS:${PN} += "dotnet"


do_build() {
    
        dotnet msbuild ${WORKDIR}/git/src/Avalonia.Samples/Avalonia.Samples.sln
}

do_compile() {
}
#do_install() {
#        install -d "${D}${bindir}"
#	install -d "${D}${libdir}/helloworld/.debug"
#        install -m 0755 ${S}/bin/${CONFIGURATION}/*.mdb ${D}${libdir}/helloworld/.debug
#        install -m 0755 ${S}/bin/${CONFIGURATION}/*.exe ${D}${libdir}/helloworld
#
#        install -m 0755 ${S}/script.in ${D}${bindir}/helloworld
#        sed -i "s|@MONO@|mono|g" ${D}${bindir}/helloworld
#        sed -i "s|@prefix@|/usr|g" ${D}${bindir}/helloworld
#        sed -i "s|@APP@|helloworld|g" ${D}${bindir}/helloworld
#        install -m 0755 ${S}/script.in ${D}${bindir}/helloworldform
#        sed -i "s|@MONO@|mono|g" ${D}${bindir}/helloworldform
#        sed -i "s|@prefix@|/usr|g" ${D}${bindir}/helloworldform
#        sed -i "s|@APP@|helloworld|g" ${D}${bindir}/helloworldform
#}


