SUMMARY = "avalonia Simple ToDo List"
DESCRIPTION = "Test applications for avalonia"
AUTHOR = "Olivier, PERRIER olivierperrier@yahoo.fr>"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit autotools
inherit mono
SRC_URI = "git://github.com/AvaloniaUI/Avalonia.Samples.git;protocol=https;branch=main"

SRCREV="2b547d92343081011dfa93e815acd1dd0b92bb31"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SUMMARY = "avalonia sample application build"
DESCRIPTION = "Build and install avalonia samples"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Entering bitbake build avalonia-samples    *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}


DEPENDS += "dotnet-native"
# Note for self-contained compilation dotnet can be removed from RDEPENDS
RDEPENDS:${PN}:append = " \
    dotnet \
    icu \
    libgssapi-krb5 \
    zlib \
"

SRC_ARCH:aarch64 = "arm64"
SRC_ARCH:arm = "arm"
SRC_ARCH:x86-64 = "x64"

INSANE_SKIP:${PN} += "\
    already-stripped \
    staticdev \
"
do_compile[network]="1"
do_compile() {
        #dotnet restore ${WORKDIR}/git/src/Avalonia.Samples/CompleteApps/SimpleToDoList/SimpleToDoList.csproj
        #dotnet publish ${WORKDIR}/git/src/Avalonia.Samples/CompleteApps/SimpleToDoList/SimpleToDoList.csproj -c Release -o publish -r linux-arm -p:PublishReadyToRun=true -p:PublishSingleFile=true -p:PublishTrimmed=true --self-contained true -p:IncludeNativeLibrariesForSelfExtract=true --verbosity detailed
        dotnet build ${WORKDIR}/git/src/Avalonia.Samples/CompleteApps/${PN}/SimpleToDoList.csproj --output ${B}/${PN} --configuration release --runtime linux-${SRC_ARCH}

        #dotnet build ${WORKDIR}/git/src/Avalonia.Samples/Avalonia.Samples.sln  --no-restore --output ${B}/${PN} --configuration release
}

do_install() {
    install -d ${D}/opt/
    cp -r --no-preserve=ownership ${B}/${PN}/${PN} ${D}/opt

#    if [ "${SRC_ARCH}" = "x64" ]; then
#        ln -s ${base_libdir} ${D}/lib64
#    fi
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
}


addtask do_display_banner before do_compile