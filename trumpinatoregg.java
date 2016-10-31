package trumpmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class trumpinatoregg extends EntityThrowable
{
    public trumpinatoregg(World worldIn)
    {
        super(worldIn);
    }

    public trumpinatoregg(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public trumpinatoregg(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void func_189664_a(DataFixer p_189664_0_)
    {
        EntityThrowable.func_189661_a(p_189664_0_, "ThrownEgg");
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void func_70184_a(RayTraceResult result)
    {
        if (result.field_72308_g != null)
        {
            result.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 0.0F);
        }

        if (!this.field_70170_p.field_72995_K)
        {
                trumpinator entitychicken = new trumpinator(this.field_70170_p);
                entitychicken.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
                this.field_70170_p.func_72838_d(entitychicken);
        }

        double d0 = 0.08D;

        for (int k = 0; k < 8; ++k)
        {
            this.field_70170_p.func_175688_a(EnumParticleTypes.ITEM_CRACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, new int[] {Item.func_150891_b(Items.field_151110_aK)});
        }

        if (!this.field_70170_p.field_72995_K)
        {
            this.func_70106_y();
        }
        
    }
}
