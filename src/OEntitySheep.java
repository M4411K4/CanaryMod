import java.util.Random;


public class OEntitySheep extends OEntityAnimal {
    public static final float[][] a = { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
    private int b;
    private OEntityAIEatGrass c = new OEntityAIEatGrass(this);

    public OEntitySheep(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/sheep.png";
        b(0.9F, 1.3F);

        float f = 0.23F;

        al().a(true);
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIPanic(this, 0.38F));
        this.aL.a(2, new OEntityAIMate(this, f));
        this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
        this.aL.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.aL.a(5, this.c);
        this.aL.a(6, new OEntityAIWander(this, f));
        this.aL.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(8, new OEntityAILookIdle(this));
    }

    protected boolean c_() {
        return true;
    }

    protected void z_() {
        this.b = this.c.f();
        super.z_();
    }

    public void e() {
        if (this.bi.F) {
            this.b = Math.max(0, this.b - 1);
        }
        super.e();
    }

    public int d() {
        return 8;
    }

    protected void b() {
        super.b();

        this.bY.a(16, new Byte((byte) 0));
    }

    protected void a(boolean flag, int i) {
        if (!A_()) {
            a(new OItemStack(OBlock.ab.bO, 1, x()), 0.0F);
        }
    }

    protected int f() {
        return OBlock.ab.bO;
    }

    public boolean b(OEntityPlayer oentityplayer) {
        OItemStack localOItemStack = oentityplayer.k.d();

        if ((localOItemStack != null) && (localOItemStack.c == OItem.bd.bP) && (!A_()) && (!aO())) {
            if (!this.bi.F) {
                a(true);
                int i = 1 + this.bS.nextInt(3);

                for (int j = 0; j < i; j++) {
                    OEntityItem localOEntityItem = a(new OItemStack(OBlock.ab.bO, 1, x()), 1.0F);

                    localOEntityItem.bq += this.bS.nextFloat() * 0.05F;
                    localOEntityItem.bp += (this.bS.nextFloat() - this.bS.nextFloat()) * 0.1F;
                    localOEntityItem.br += (this.bS.nextFloat() - this.bS.nextFloat()) * 0.1F;
                }
            }
            localOItemStack.a(1, oentityplayer);
        }

        return super.b(oentityplayer);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Sheared", A_());
        onbttagcompound.a("Color", (byte) x());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        a(onbttagcompound.o("Sheared"));
        d_(onbttagcompound.d("Color"));
    }

    protected String i() {
        return "mob.sheep";
    }

    protected String j() {
        return "mob.sheep";
    }

    protected String k() {
        return "mob.sheep";
    }

    public int x() {
        return this.bY.a(16) & 0xF;
    }

    public void d_(int i) {
        int j = this.bY.a(16);

        this.bY.b(16, Byte.valueOf((byte) (j & 0xF0 | i & 0xF)));
    }

    public boolean A_() {
        return (this.bY.a(16) & 0x10) != 0;
    }

    public void a(boolean flag) {
        int i = this.bY.a(16);

        if (flag) {
            this.bY.b(16, Byte.valueOf((byte) (i | 0x10)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (i & 0xFFFFFFEF)));
        }
    }

    public static int a(Random random) {
        int i = random.nextInt(100);

        if (i < 5) {
            return 15;
        }
        if (i < 10) {
            return 7;
        }
        if (i < 15) {
            return 8;
        }
        if (i < 18) {
            return 12;
        }
        if (random.nextInt(500) == 0) {
            return 6;
        }
        return 0;
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntitySheep localOEntitySheep1 = (OEntitySheep) oentityanimal;
        OEntitySheep localOEntitySheep2 = new OEntitySheep(this.bi);

        if (this.bS.nextBoolean()) {
            localOEntitySheep2.d_(x());
        } else {
            localOEntitySheep2.d_(localOEntitySheep1.x());
        }
        return localOEntitySheep2;
    }

    public void z() {
        a(false);
        if (aO()) {
            int i = K() + 1200;

            if (i > 0) {
                i = 0;
            }
            c(i);
        }
    }
}
