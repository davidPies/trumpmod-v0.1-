package trumpmod;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class trump extends EntityCow implements net.minecraftforge.common.IShearable
{
	private static final Set<Item> ITEMS = Sets.newHashSet(new Item[] {trumpmod.money});
	//private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.<Integer>createKey(trump.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.<Byte>func_187226_a(trump.class, DataSerializers.field_187191_a);
	private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);
	private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container()
    {
        public boolean func_75145_c(EntityPlayer playerIn)
        {
            return true;
        }
    }, 2, 1);
	public static float[] getDyeRgb(EnumDyeColor dyeColor)
    {
        return (float[])DYE_TO_RGB.get(dyeColor);
    }
    public trump(World worldIn)
    {
        super(worldIn);
        this.func_70105_a(0.9F, 1.4F);
        this.inventoryCrafting.func_70299_a(0, new ItemStack(Items.field_151100_aR));
        this.inventoryCrafting.func_70299_a(1, new ItemStack(Items.field_151100_aR));
    }
   protected void func_70088_a()
   {
	   super.func_70088_a();
	    this.field_70180_af.func_187214_a(DYE_COLOR, Byte.valueOf((byte)0));
        //this.dataManager.register(COLLAR_COLOR, Integer.valueOf(EnumDyeColor.YELLOW.getDyeDamage()));
   }
   //public EnumDyeColor getCollarColor()
   // {
   //     return EnumDyeColor.byDyeDamage(((Integer)this.dataManager.get(COLLAR_COLOR)).intValue() & 15);
   // }

   //public void setCollarColor(EnumDyeColor collarcolor)
    //{
   //     this.dataManager.set(COLLAR_COLOR, Integer.valueOf(collarcolor.getDyeDamage()));
    //}
    public void func_70014_b(NBTTagCompound compound)
    {
        super.func_70014_b(compound);
     //   compound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
        compound.func_74757_a("Sheared", this.getSheared());
        compound.func_74774_a("Color", (byte)this.getFleeceColor().func_176765_a());
   }
    public void func_70037_a(NBTTagCompound compound)
    {
        super.func_70037_a(compound);
        this.setSheared(compound.func_74767_n("Sheared"));
        this.setFleeceColor(EnumDyeColor.func_176764_b(compound.func_74771_c("Color")));
       // if (compound.hasKey("CollarColor", 99))
       //{
       //     this.setCollarColor(EnumDyeColor.byDyeDamage(compound.getByte("CollarColor")));
       // }
    }
    public static void func_189790_b(DataFixer p_189790_0_)
    {
        EntityLiving.func_189752_a(p_189790_0_, "trump");
    }
    public boolean getSheared()
    {
        return (((Byte)this.field_70180_af.func_187225_a(DYE_COLOR)).byteValue() & 16) != 0;
    }
    public void setSheared(boolean sheared)
    {
        byte b0 = ((Byte)this.field_70180_af.func_187225_a(DYE_COLOR)).byteValue();

        if (sheared)
        {
            this.field_70180_af.func_187227_b(DYE_COLOR, Byte.valueOf((byte)(b0 | 16)));
        }
        else
        {
            this.field_70180_af.func_187227_b(DYE_COLOR, Byte.valueOf((byte)(b0 & -17)));
        }
    }
    protected void func_184651_r()
    {
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 2.0D));
        //this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.2D, trumpmod.money, false));
        this.field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.2D, false, ITEMS));
        this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.25D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
    }

    protected SoundEvent func_184639_G()
    {
        return SoundEvents.field_187913_gm;
    }

    protected SoundEvent func_184601_bQ()
    {
        return SoundEvents.field_187800_eb;
    }

    protected SoundEvent func_184615_bR()
    {
        return SoundEvents.field_187798_ea;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn)
    {
        this.func_184185_a(SoundEvents.field_187765_eK, 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float func_70599_aP()
    {
        return 0.4F;
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
    if (stack != null){
        if (stack.func_77973_b() == trumpmod.snatcher && !this.func_70631_g_())
        {
            player.func_184185_a(SoundEvents.field_187728_s, 1.0F, 1.0F);

            if (--stack.field_77994_a == 0)
            {
                player.func_184611_a(hand, new ItemStack(trumpmod.money, 3));
               
            }
            return true;
            //if (!player.inventory.addItemStackToInventory(new ItemStack(trumpmod.money)))
            //{
            //    player.dropItem(new ItemStack(trumpmod.money), false);
            //}
        }
        else if (stack.func_77973_b() == Items.field_151100_aR)
        {
        	player.func_184185_a(SoundEvents.field_187728_s, 1.0F, 1.0F);
            EnumDyeColor enumdyecolor = EnumDyeColor.func_176766_a(stack.func_77960_j());

            if (enumdyecolor != this.getFleeceColor())
            {
                this.setFleeceColor(enumdyecolor);

                if (!player.field_71075_bZ.field_75098_d)
                {
                    --stack.field_77994_a;
                }
                return true;
            }
        }
        if (stack != null && stack.func_77973_b() == Items.field_151097_aZ && !this.getSheared() && !this.func_70631_g_())
        {
            if (!this.field_70170_p.field_72995_K)
            {
                this.setSheared(true);
                int i = 1 + this.field_70146_Z.nextInt(3);

                for (int j = 0; j < i; ++j)
                {
                    EntityItem entityitem = this.func_70099_a(new ItemStack(trumpmod.trumphair2_1, 1, this.getFleeceColor().func_176765_a()), 1.0F);
                    entityitem.field_70181_x += (double)(this.field_70146_Z.nextFloat() * 0.05F);
                    entityitem.field_70159_w += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F);
                    entityitem.field_70179_y += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F);
                }
            }

            stack.func_77972_a(1, player);
            this.func_184185_a(SoundEvents.field_187763_eJ, 1.0F, 1.0F);
        }
    }
            return super.func_184645_a(player, hand, stack);
        }

    public trump func_90011_a(EntityAgeable ageable)
    {
    	trump entitysheep = (trump)ageable;
        trump entitysheep1 = new trump(this.field_70170_p);
        entitysheep1.setFleeceColor(this.getDyeColorMixFromParents(this, entitysheep));
        return entitysheep1;
    }

    public float func_70047_e()
    {
        return this.func_70631_g_() ? this.field_70131_O : 1.3F;
    }

	public boolean isVillager() {
		return false;
	}
	@Nullable
	protected ResourceLocation func_184647_J()
    {
        if (this.getSheared())
        {
            return new ResourceLocation("trumpmod:entities/hairs");
        }
        else
        {
            switch (this.getFleeceColor())
            { 
                case WHITE:
                default:
                	return new ResourceLocation("trumpmod:entities/white");
                case ORANGE:
                	return new ResourceLocation("trumpmod:entities/orange");
                case MAGENTA:
                	return new ResourceLocation("trumpmod:entities/magenta");
                case LIGHT_BLUE:
                	return new ResourceLocation("trumpmod:entities/light_blue");
                case YELLOW:
                	return new ResourceLocation("trumpmod:entities/yellow");
                case LIME:
                	return new ResourceLocation("trumpmod:entities/lime");
                case PINK:
                	return new ResourceLocation("trumpmod:entities/pink");
                case GRAY:
                	return new ResourceLocation("trumpmod:entities/gray");
                case SILVER:
                	return new ResourceLocation("trumpmod:entities/silver");
                case CYAN:
                	return new ResourceLocation("trumpmod:entities/cyan");
                case PURPLE:
                	return new ResourceLocation("trumpmod:entities/purple");
                case BLUE:
                	return new ResourceLocation("trumpmod:entities/blue");
                case BROWN:
                	return new ResourceLocation("trumpmod:entities/brown");
                case GREEN:
                	return new ResourceLocation("trumpmod:entities/green");
                case RED:
                	return new ResourceLocation("trumpmod:entities/red");
                case BLACK:
                	return new ResourceLocation("trumpmod:entities/black");
            }
        }}
	public static EnumDyeColor getRandomSheepColor(Random random)
    {
        int i = random.nextInt(100);
        return i < 15 ? EnumDyeColor.YELLOW : (i < 10 ? EnumDyeColor.WHITE : (i < 25 ? EnumDyeColor.BROWN : (i < 50 ? EnumDyeColor.BLACK : (random.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.YELLOW))));
    }
	@Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.func_180482_a(difficulty, livingdata);
        this.setFleeceColor(getRandomSheepColor(this.field_70170_p.field_73012_v));
        return livingdata;
    }
	private EnumDyeColor getDyeColorMixFromParents(trump father, trump mother)
    {
        int i = (father).getFleeceColor().func_176767_b();
        int j = (mother).getFleeceColor().func_176767_b();
        this.inventoryCrafting.func_70301_a(0).func_77964_b(i);
        this.inventoryCrafting.func_70301_a(1).func_77964_b(j);
        ItemStack itemstack = CraftingManager.func_77594_a().func_82787_a(this.inventoryCrafting, (father).field_70170_p);
        int k;

        if (itemstack != null && itemstack.func_77973_b() == Items.field_151100_aR)
        {
            k = itemstack.func_77960_j();
        }
        else
        {
            k = this.field_70170_p.field_73012_v.nextBoolean() ? i : j;
        }

        return EnumDyeColor.func_176766_a(k);
    }
	@Override public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos){ return !this.getSheared() && !this.func_70631_g_(); }
    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        this.setSheared(true);
        int i = 1 + this.field_70146_Z.nextInt(3);

        java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        for (int j = 0; j < i; ++j)
            ret.add(new ItemStack(trumpmod.trumphair2_1, 1, this.getFleeceColor().func_176765_a()));

        this.func_184185_a(SoundEvents.field_187763_eJ, 1.0F, 1.0F);
        return ret;
    }
    static
    {
        DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[] {1.0F, 1.0F, 1.0F});
        DYE_TO_RGB.put(EnumDyeColor.ORANGE, new float[] {0.85F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.MAGENTA, new float[] {0.7F, 0.3F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.LIGHT_BLUE, new float[] {0.4F, 0.6F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.YELLOW, new float[] {0.9F, 0.9F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.LIME, new float[] {0.5F, 0.8F, 0.1F});
        DYE_TO_RGB.put(EnumDyeColor.PINK, new float[] {0.95F, 0.5F, 0.65F});
        DYE_TO_RGB.put(EnumDyeColor.GRAY, new float[] {0.3F, 0.3F, 0.3F});
        DYE_TO_RGB.put(EnumDyeColor.SILVER, new float[] {0.6F, 0.6F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.CYAN, new float[] {0.3F, 0.5F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.PURPLE, new float[] {0.5F, 0.25F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BLUE, new float[] {0.2F, 0.3F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BROWN, new float[] {0.4F, 0.3F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.GREEN, new float[] {0.4F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.RED, new float[] {0.6F, 0.2F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.BLACK, new float[] {0.1F, 0.1F, 0.1F});
    }
    public EnumDyeColor getFleeceColor()
    {
        return EnumDyeColor.func_176764_b(((Byte)this.field_70180_af.func_187225_a(DYE_COLOR)).byteValue() & 15);
    }
    public void setFleeceColor(EnumDyeColor color)
    {
        byte b0 = ((Byte)this.field_70180_af.func_187225_a(DYE_COLOR)).byteValue();
        this.field_70180_af.func_187227_b(DYE_COLOR, Byte.valueOf((byte)(b0 & 240 | color.func_176765_a() & 15)));
    }
}
