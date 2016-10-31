package trumpmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class tabs extends Item{
	public static final CreativeTabs trumpmodtab = new CreativeTabs("trumpmodtab") {
	    @Override public Item func_78016_d() {
	        return trumpmod.trumphair2_1;
	    }
	};
}
