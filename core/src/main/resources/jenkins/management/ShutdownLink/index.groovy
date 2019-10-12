package jenkins.management.ShutdownNewLink

import jenkins.management.Messages

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
        h1 {
            l.icon(class: 'icon-system-log-out icon-xlg')
            text(Messages.ShutdownLink_DisplayName_prepare())
        }

        p {
            text(my.description)
        }

        f.form(method: "post", name: "prepareQuietDown", action: "prepare") {
            f.entry(title: Messages.ShutdownLink_QuietDownReason_title()) {
                f.textbox(name: "parameter.quietReason", value: app.quietDownReason ?: null)
            }


            f.bottomButtonBar {
                f.submit(value: _(app.isQuietingDown()
                        ? Messages.ShutdownLink_QuietDownReason_update()
                        : Messages.ShutdownLink_DisplayName_prepare()))
            }
        }

        if (app.isQuietingDown()) {
            f.form(method: "post", name: "cancelQuietDown", action: "cancel") {
                f.bottomButtonBar {
                    f.submit(value: _(Messages.ShutdownLink_DisplayName_cancel()))
                }
            }
        }
    }
}
