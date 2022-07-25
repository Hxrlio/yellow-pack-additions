package com.hxrl.yellowpack.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Setup {
	
	public static final CreativeModeTab ITEM_TAB = new CreativeModeTab("yellowpack") {
		
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.DULLBLADE.get());
        }
        
    };
    
}
