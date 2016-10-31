package trumpmod;

import net.minecraft.item.ItemSword;

import net.minecraft.item.Item.ToolMaterial;

public class thepoll extends ItemSword {

public thepoll(String unlocalizedName, ToolMaterial material) {
    super(material);
    this.func_77655_b(unlocalizedName);
    this.func_77637_a(tabs.trumpmodtab);
}
}
