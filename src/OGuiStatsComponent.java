import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.Timer;


public class OGuiStatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");
    private int[] b = new int[256];
    private int c = 0;
    private String[] d = new String[10];
    private final OMinecraftServer e;

    public OGuiStatsComponent(OMinecraftServer ominecraftserver) {
        super();
        this.e = ominecraftserver;
        this.setPreferredSize(new Dimension(356, 246));
        this.setMinimumSize(new Dimension(356, 246));
        this.setMaximumSize(new Dimension(356, 246));
        (new Timer(500, new OGuiStatsListener(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        this.d[0] = "Memory use: " + i / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.d[1] = "Threads: " + ONetworkManager.b + " + " + ONetworkManager.c;
        this.d[2] = "Avg tick: " + a.format(this.a(this.e.f) * 1.0E-6D) + " ms";
        this.d[3] = "Avg sent: " + (int) this.a(this.e.u) + ", Avg size: " + (int) this.a(this.e.v);
        this.d[4] = "Avg rec: " + (int) this.a(this.e.w) + ", Avg size: " + (int) this.a(this.e.x);
        if (this.e.worlds != null) {
            // CanaryMod start: Multiworld
            for (Map.Entry<String, OWorldServer[]> entry : this.e.worlds.entrySet()) {
                String worldName = entry.getKey();
                OWorldServer[] level = entry.getValue();

                for (int j = 0; j < level.length; ++j) {
                    this.d[5 + j] = "World " + worldName + " lvl " + j + " tick: " + a.format(this.a(this.e.worldTickNanos.get(worldName)[j]) * 1.0E-6D) + " ms";
                    if (level[j] != null && level[j].G != null) {
                        this.d[5 + j] += ", " + level[j].G.d();
                    }
                }
            }
            // CanaryMod end
        }

        this.b[this.c++ & 255] = (int) (this.a(this.e.v) * 100.0D / 12500.0D);
        this.repaint();
    }

    private double a(long[] along) {
        long i = 0L;

        for (int j = 0; j < along.length; ++j) {
            i += along[j];
        }

        return (double) i / (double) along.length;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(new Color(16777215));
        graphics.fillRect(0, 0, 356, 246);

        int i;

        for (i = 0; i < 256; ++i) {
            int j = this.b[i + this.c & 255];

            graphics.setColor(new Color(j + 28 << 16));
            graphics.fillRect(i, 100 - j, 1, j);
        }

        graphics.setColor(Color.BLACK);

        for (i = 0; i < this.d.length; ++i) {
            String s = this.d[i];

            if (s != null) {
                graphics.drawString(s, 32, 116 + i * 16);
            }
        }

    }

    // $FF: synthetic method
    static void a(OGuiStatsComponent oguistatscomponent) {
        oguistatscomponent.a();
    }

}
