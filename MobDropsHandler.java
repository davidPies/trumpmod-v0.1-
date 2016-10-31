package trumpmod;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDropsHandler
{
	public ItemStack a(ItemStack a, ItemStack b){
		return a;
		
	}
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof trump)
        {
            event.getDrops().clear();
            ItemStack stack = new ItemStack(trumpmod.hair2, 3);
            EntityItem drop = new EntityItem(event.getEntity().field_70170_p, event.getEntity().field_70165_t, event.getEntity().field_70163_u, event.getEntity().field_70161_v, stack);
 
            event.getDrops().add(drop);
        }
        if (event.getEntity() instanceof EntityV)
        {
            event.getDrops().clear();
            ItemStack stack = new ItemStack(trumpmod.money, 2);
            EntityItem drop = new EntityItem(event.getEntity().field_70170_p, event.getEntity().field_70165_t, event.getEntity().field_70163_u, event.getEntity().field_70161_v, stack);
 
            event.getDrops().add(drop);
        }
        if (event.getEntity() instanceof trumpinator)
        {
            event.getDrops().clear();
 
            ItemStack stack = new ItemStack(Items.field_151042_j, 2);
            EntityItem drop = new EntityItem(event.getEntity().field_70170_p, event.getEntity().field_70165_t, event.getEntity().field_70163_u, event.getEntity().field_70161_v, stack);
 
            event.getDrops().add(drop);
        }
    }}
    //@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
