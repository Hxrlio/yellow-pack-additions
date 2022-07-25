package com.hxrl.yellowpack.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import com.hxrl.yellowpack.setup.Setup;

public class DullBlade extends Item {

	public DullBlade() {
		super(new Item.Properties().stacksTo(1).tab(Setup.ITEM_TAB));
	}
	
	@Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("yellowpack.tooltip.dull_blade"));
    }

}
