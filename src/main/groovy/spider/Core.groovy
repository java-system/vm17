package spider

import system.Dynamic
import system.GroovyVM

class Core {
    def vm = null
    def settings = null
    Core(GroovyVM vm) {
        this.vm = vm
        this.settings = loadSettings("spider-explorer")
    }
    def info() {
        return settings.info
    }
    def save() {
        this.saveSettings(this.settings)
    }
    protected def loadSettings(product) {
        def settings = [:]
        this.settings = settings
        def userHomeDir = System.getProperty("user.home")
        settings.userHomeDir = userHomeDir
        println "The User Home Directory is $userHomeDir"
        String settingsPath = userHomeDir + "/.apps/$product/settings.json";
        settings.path = settingsPath
        def obj = vm.fromJson(vm.readStringFromFile(settingsPath, "{}"))
        settings.info = obj
        return settings
    }
    protected def saveSettings(settings) {
        def path = settings.path
        vm.writeStringToFile(path, vm.toJson(settings.info))
    }
}
