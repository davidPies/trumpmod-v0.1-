package trumpmod;

import net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;

public class trades {

	 public static final ITradeList[] trades =
			 
		 {
				 new EmeraldForItems(trumpmod.grabber, new PriceInfo(7, 10)),
				 //new ListItemForEmeralds(MoDropsItems.raw_horse_meat, new PriceInfo(-6, -2))
		 };
}
