package jenkins.management.ShutdownNewLink

def f = namespace(lib.FormTagLib)
def l = namespace(lib.LayoutTagLib)
def st = namespace("jelly:stapler")

l.layout(norefresh: true, permission: app.ADMINISTER, title: my.displayName) {
    l.side_panel {
        l.tasks {
            l.task(icon: "icon-up icon-md", href: rootURL + '/', title: _("Back to Dashboard"))
            l.task(icon: "icon-gear2 icon-md", href: "${rootURL}/manage", title: _("Manage Jenkins"))
        }
    }
    l.main_panel {
//        h1 {
//            l.icon(class: 'icon-setting icon-xlg')
//            // TODO more appropriate icon
//            text(my.displayName)
//        }

        p()

        f.form(method: "post", name: "prepareShutdown", action: "prepare", enctype: "multipart/form-data") {
            f.entry(title: "My title") {
                f.textbox(name: "parameter.shutdownReason")
            }
//                    {
//                name = "shutdownReason"
//                value = app.getQuietDownReason != null ? app.getQuietDownReason : null
//            }

            f.bottomButtonBar {
                f.submit(value: _("Save"))
                f.apply(value: _("Apply"))
            }
        }
    }
}
