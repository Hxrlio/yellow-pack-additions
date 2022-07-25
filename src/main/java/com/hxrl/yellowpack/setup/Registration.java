package com.hxrl.yellowpack.setup;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.hxrl.yellowpack.item.DullBlade;

import static com.hxrl.yellowpack.YellowPack.MODID;

public class Registration {
	
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	
	public static void init() {
		
		IEventBus mbus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ITEMS.register(mbus);
		
	}
	
	public static final RegistryObject<DullBlade> DULLBLADE = ITEMS.register("dull_blade", DullBlade::new);
}
