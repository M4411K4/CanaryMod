import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;


class OServerGuiCommandListener implements ActionListener {

    // $FF: synthetic field
    final JTextField a;
    // $FF: synthetic field
    final OServerGUI b;

    OServerGuiCommandListener(OServerGUI oservergui, JTextField jtextfield) {
        super();
        this.b = oservergui;
        this.a = jtextfield;
    }

    public void actionPerformed(ActionEvent actionevent) {
        String s = this.a.getText().trim();

        // CanaryMod: parse our commands first.
        if (s.length() > 0 && etc.getMCServer() != null) {
            if (!etc.getInstance().parseConsoleCommand(s, etc.getMCServer())) {
                etc.getMCServer().a(s, this.b);
            }
        }

        this.a.setText("");
    }
}
