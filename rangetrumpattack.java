package trumpmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;

public class rangetrumpattack extends EntityAIBase
{
    private final trumpinator entity;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private final float maxAttackDistance;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public rangetrumpattack(trumpinator skeleton, double speedAmplifier, int delay, float maxDistance)
    {
        this.entity = skeleton;
        this.moveSpeedAmp = speedAmplifier;
        this.attackCooldown = delay;
        this.maxAttackDistance = maxDistance * maxDistance;
        this.func_75248_a(3);
    }

    public void setAttackCooldown(int p_189428_1_)
    {
        this.attackCooldown = p_189428_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean func_75250_a()
    {
        return this.entity.func_70638_az() == null ? false : this.isBowInMainhand();
    }

    protected boolean isBowInMainhand()
    {
        return this.entity.func_184614_ca() != null && this.entity.func_184614_ca().func_77973_b() == Items.field_151031_f;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean func_75253_b()
    {
        return (this.func_75250_a() || !this.entity.func_70661_as().func_75500_f()) && this.isBowInMainhand();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void func_75249_e()
    {
        super.func_75249_e();
        //this.entity.setSwingingArms(true);
    }

    /**
     * Resets the task
     */
    public void func_75251_c()
    {
        super.func_75251_c();
        //this.entity.setSwingingArms(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.func_184602_cy();
    }

    /**
     * Updates the task
     */
    public void func_75246_d()
    {
        EntityLivingBase entitylivingbase = this.entity.func_70638_az();

        if (entitylivingbase != null)
        {
            double d0 = this.entity.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
            boolean flag = this.entity.func_70635_at().func_75522_a(entitylivingbase);
            boolean flag1 = this.seeTime > 0;

            if (flag != flag1)
            {
                this.seeTime = 0;
            }

            if (flag)
            {
                ++this.seeTime;
            }
            else
            {
                --this.seeTime;
            }

            if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
            {
                this.entity.func_70661_as().func_75499_g();
                ++this.strafingTime;
            }
            else
            {
                this.entity.func_70661_as().func_75497_a(entitylivingbase, this.moveSpeedAmp);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20)
            {
                if ((double)this.entity.func_70681_au().nextFloat() < 0.3D)
                {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.entity.func_70681_au().nextFloat() < 0.3D)
                {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1)
            {
                if (d0 > (double)(this.maxAttackDistance * 0.75F))
                {
                    this.strafingBackwards = false;
                }
                else if (d0 < (double)(this.maxAttackDistance * 0.25F))
                {
                    this.strafingBackwards = true;
                }

                this.entity.func_70605_aq().func_188488_a(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.entity.func_70625_a(entitylivingbase, 30.0F, 30.0F);
            }
            else
            {
                this.entity.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
            }

            if (this.entity.func_184587_cr())
            {
                if (!flag && this.seeTime < -60)
                {
                    this.entity.func_184602_cy();
                }
                else if (flag)
                {
                    int i = this.entity.func_184612_cw();

                    if (i >= 20)
                    {
                        this.entity.func_184602_cy();
                        this.entity.attackEntityWithRangedAttack(entitylivingbase, ItemBow.func_185059_b(i));
                        this.attackTime = this.attackCooldown;
                    }
                }
            }
            else if (--this.attackTime <= 0 && this.seeTime >= -60)
            {
                this.entity.func_184598_c(EnumHand.MAIN_HAND);
            }
        }
    }
}
