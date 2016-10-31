package trumpmod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class trumpinator extends EntityCow implements IMob{
	private final rangetrumpattack aiArrowAttack = new rangetrumpattack(this, 1.0D, 20, 20.0F);
    private final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false);
    public trumpinator(World worldIn) {
		super(worldIn);
        this.func_70105_a(0.9F, 1.4F);
        this.setCombatTask();
		this.func_184611_a(func_184600_cs().MAIN_HAND, new ItemStack(Items.field_151031_f));
	}
	protected SoundEvent func_184639_G()
    {
        return SoundEvents.field_187668_ca;
    }

    protected SoundEvent func_184601_bQ()
    {
        return SoundEvents.field_187770_dm;
    }

    protected SoundEvent func_184615_bR()
    {
        return SoundEvents.field_187766_dk;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn)
    {
        this.func_184185_a(SoundEvents.field_187778_dq, 0.15F, 1.0F);
    }
	public trumpinator func_90011_a(EntityAgeable ageable)
    {
		return new trumpinator(this.field_70170_p);
    }
	protected void func_184651_r()
    {
		this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
		this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, trump.class, true));
		this.field_70714_bg.func_75776_a(5, new followplayer(this, 1.0D, 10.0F, 2.0F));
		this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
		//this.tasks.addTask(2, new EntityAIZombieAttack(this, 1.0D, false));
    }
	protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(35.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(35.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(2.0D);
        }
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase, float p_82196_2_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.field_70170_p, this);
        double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
        double d1 = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 3.0F) - entitytippedarrow.field_70163_u;
        double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
        double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2);
        entitytippedarrow.func_70186_c(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.field_70170_p.func_175659_aa().func_151525_a() * 4));
        int i = EnchantmentHelper.func_185284_a(Enchantments.field_185309_u, this);
        int j = EnchantmentHelper.func_185284_a(Enchantments.field_185310_v, this);
        DifficultyInstance difficultyinstance = this.field_70170_p.func_175649_E(new BlockPos(this));
        entitytippedarrow.func_70239_b((double)(p_82196_2_ * 2.0F) + this.field_70146_Z.nextGaussian() * 0.25D + (double)((float)this.field_70170_p.func_175659_aa().func_151525_a() * 0.11F));

        if (i > 0)
        {
            entitytippedarrow.func_70239_b(entitytippedarrow.func_70242_d() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entitytippedarrow.func_70240_a(j);
        }

        ItemStack itemstack = this.func_184586_b(EnumHand.OFF_HAND);
        this.func_184185_a(SoundEvents.field_187866_fi, 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
        this.field_70170_p.func_72838_d(entitytippedarrow);
    }
	public boolean func_70601_bi()
    {
        return true;
    }
	public void setCombatTask()
    {
        if (this.field_70170_p != null && !this.field_70170_p.field_72995_K)
        {
            this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
            this.field_70714_bg.func_85156_a(this.aiArrowAttack);
            ItemStack itemstack = this.func_184614_ca();

            if (itemstack != null && itemstack.func_77973_b() == Items.field_151031_f)
            {
                int i = 20;

                if (this.field_70170_p.func_175659_aa() != EnumDifficulty.HARD)
                {
                    i = 40;
                }

                this.aiArrowAttack.setAttackCooldown(i);
                this.field_70714_bg.func_75776_a(4, this.aiArrowAttack);
            }
            else
            {
                this.field_70714_bg.func_75776_a(4, this.aiAttackOnCollide);
            }
        }
    }
	public void func_184201_a(EntityEquipmentSlot slotIn, @Nullable ItemStack stack)
    {
        super.func_184201_a(slotIn, stack);

        if (!this.field_70170_p.field_72995_K && slotIn == EntityEquipmentSlot.MAINHAND)
        {
            this.setCombatTask();
        }
    }
	protected ResourceLocation func_184647_J()
    {
        return null; 
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
}
