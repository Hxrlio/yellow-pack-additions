package com.hxrl.yellowpack.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hxrl.yellowpack.YellowPack;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = YellowPack.MODID)
public class ModEvents {
	
	private static final String name = "HandDropChances";
	private static final FloatTag ft = FloatTag.valueOf(0.0f);
	private static final ListTag tagList = new ListTag();
	
	@SubscribeEvent
	public static void modifyTrades(VillagerTradesEvent e) {
		
		if (e.getType() == VillagerProfession.TOOLSMITH) {
			
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = e.getTrades();
			List<ItemListing> lvl1 = trades.get(1);
			List<ItemListing> lvl3 = trades.get(3);
			List<ItemListing> lvl4 = trades.get(4);
			List<ItemListing> lvl5 = trades.get(5);
			
			lvl1.clear();
			lvl1.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.COAL, 15), new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
			lvl1.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.SHEARS, 1), 12, 1, 0.05F));
			
			lvl3.clear();
			lvl3.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.FLINT, 30), new ItemStack(Items.EMERALD, 1), 12, 20, 0.05F));
			Map<Integer, String[]> lvl3Kits = new HashMap<Integer, String[]>();
			String[] t1 = {"tconstruct:rock#stone", "tconstruct:flint", "tconstruct:copper"};
			String[] t2 = {"tconstruct:iron", "tconstruct:seared_stone", "tconstruct:osmium", "tconstruct:lead", "tconstruct:whitestone", "tconstruct:scorched_stone"};
			String[] t3 = {"tconstruct:amethyst_bronze", "tconstruct:rose_gold", "tconstruct:steel", "tconstruct:bronze", "tconstruct:cobalt"};
			String[] t4 = {"tconstruct:hepatizon", "tconstruct:manyullyn"};
			lvl3Kits.put(5, t1);
			lvl3Kits.put(10, t2);
			
			for (Integer costlvl3 : lvl3Kits.keySet()) {
				for (String entry : lvl3Kits.get(costlvl3)) {
					ItemStack repair = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct", "repair_kit")), 1);
					CompoundTag tag = new CompoundTag();
					tag.putString("Material", entry);
					repair.setTag(tag);
					lvl3.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, costlvl3), repair, 3, 10, 0.15F));
				}
			}
			
			lvl4.clear();
			for (String entry : t3) {
				ItemStack repair = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct", "repair_kit")), 1);
				CompoundTag tag = new CompoundTag();
				tag.putString("Material", entry);
				repair.setTag(tag);
				lvl4.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 20), repair, 3, 15, 0.15F));
			}
			
			lvl5.clear();
			for (String entry : t4) {
				ItemStack repair = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct", "repair_kit")), 1);
				CompoundTag tag = new CompoundTag();
				tag.putString("Material", entry);
				repair.setTag(tag);
				lvl5.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 30), repair, 3, 20, 0.15F));
			}
		}
		
		if (e.getType() == VillagerProfession.WEAPONSMITH) {
			
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = e.getTrades();
			List<ItemListing> lvl1 = trades.get(1);
			List<ItemListing> lvl4 = trades.get(4);
			List<ItemListing> lvl5 = trades.get(5);
			
			lvl1.clear();
			lvl1.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.COAL, 15), new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
			ItemStack dullBlade = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("yellowpack", "dull_blade")), 1);
			lvl1.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 10), dullBlade, 3, 5, 0.15F));
			
			lvl4.clear();
			lvl4.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.EMERALD, 1), 12, 30, 0.05F));
			ItemStack tconSword = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct", "sword")), 1);
			lvl4.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 10), tconSword, 3, 10, 0.15F));
			
			lvl5.clear();
			ItemStack tconCleaver = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct", "cleaver")), 1);
			lvl4.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 20), tconCleaver, 3, 20, 0.15F));
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void setHandDropChance(EntityJoinWorldEvent e) {
		if (!e.getWorld().isClientSide()) {
		    Entity ent = e.getEntity();
		    CompoundTag compTag = ent.serializeNBT();
		    if (compTag.contains(name)) {
			    compTag.remove(name);
			    if (tagList.size() == 0) {
			        tagList.add(ft);
				    tagList.add(ft);
			    }
			    compTag.put(name, tagList);
			    ent.load(compTag);
		    }
		}
	}
}
