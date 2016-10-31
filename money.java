package trumpmod;

import javax.annotation.Nullable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class money extends ItemFood
{
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public final int itemUseDuration;
    /** The amount this food item heals the player. */
    private final int healAmount;
    private final float saturationModifier;
    /** Whether wolves like this food (true for raw and cooked porkchop). */
    private final boolean isWolfsFavoriteMeat;
    /** If this field is true, the food can be consumed even if the player don't need to eat. */
    private boolean alwaysEdible;
    /** represents the potion effect that will occurr upon eating this food. Set by setPotionEffect */
    private PotionEffect potionId;
    /** probably of the set potion effect occurring */
    private float potionEffectProbability;

    public money(int amount, float saturation, boolean isWolfFood)
    {
    	super(amount, saturation, isWolfFood);
        this.itemUseDuration = 16;
        this.healAmount = amount;
        this.isWolfsFavoriteMeat = isWolfFood;
        this.saturationModifier = saturation;
        this.func_77637_a(tabs.trumpmodtab);
        this.func_77655_b("money");
        this.func_77625_d(100);
    }

    public money(int amount, boolean isWolfFood)
    {
        this(amount, 0.6F, isWolfFood);
    } 

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Nullable
    public ItemStack func_77654_b(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        --stack.field_77994_a;

        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.func_71024_bL().func_151686_a(this, stack);
            worldIn.func_184148_a((EntityPlayer)null, entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.5F, worldIn.field_73012_v.nextFloat() * 0.1F + 0.9F);
            this.func_77849_c(stack, worldIn, entityplayer);
            entityplayer.func_71029_a(StatList.func_188057_b(this));
        }

        return stack;
    }

    protected void func_77849_c(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.field_72995_K && this.potionId != null && worldIn.field_73012_v.nextFloat() < this.potionEffectProbability)
        {
            player.func_70690_d(new PotionEffect(this.potionId));
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int func_77626_a(ItemStack stack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction func_77661_b(ItemStack stack)
    {
        return EnumAction.EAT;
    }

    public ActionResult<ItemStack> func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (playerIn.func_71043_e(this.alwaysEdible))
        {
            playerIn.func_184598_c(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
    }

    public int func_150905_g(ItemStack stack)
    {
        return this.healAmount;
    }

    public float func_150906_h(ItemStack stack)
    {
        return this.saturationModifier;
    }

    /**
     * Whether wolves like this food (true for raw and cooked porkchop).
     */
    public boolean func_77845_h()
    {
        return this.isWolfsFavoriteMeat;
    }

    public money func_185070_a(PotionEffect p_185070_1_, float p_185070_2_)
    {
        this.potionId = p_185070_1_;
        this.potionEffectProbability = p_185070_2_;
        return this;
    }

    /**
     * Set the field 'alwaysEdible' to true, and make the food edible even if the player don't need to eat.
     */
    public money func_77848_i()
    {
        this.alwaysEdible = true;
        return this;
    }
}
