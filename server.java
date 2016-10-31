package trumpmod;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class server extends sharedproxy{

	@EventHandler
    public void pre(FMLPreInitializationEvent e){
		super.pre(e);
    }
	@EventHandler
    public void init(FMLInitializationEvent e){
		super.init(e);
    }
}
