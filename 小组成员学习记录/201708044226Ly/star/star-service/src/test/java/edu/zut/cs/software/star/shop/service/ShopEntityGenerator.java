package edu.zut.cs.software.star.shop.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.zut.cs.software.base.service.GenericGenerator;
import edu.zut.cs.software.star.commodity.domain.Commodity;
import edu.zut.cs.software.star.commodity.service.CommodityManager;
import edu.zut.cs.software.star.shop.domain.Shop;

/**
 * 
 * 无事物回滚
 * 商店 -- 商品
 * @author 研哥哥
 *
 */
public class ShopEntityGenerator extends GenericGenerator {

	
	@Autowired
	CommodityManager commodityManager;
	@Autowired
	ShopManager shopManager;
	
	@Test
	public void gen_shop() {
		for(int i = 0; i < 10; i++) {
			Shop s = new Shop();
			s.setName("shop_" + i);
			this.shopManager.save(s);
			for(int j = 0; j < 10; j++) {
				Shop shop = new Shop();
				shop.setName("shop_" + i + "_" + j);
				shop.setParent(s);
				s = this.shopManager.save(shop);
				this.gen_commodity(s);
			}
		}
	}
	
	public void gen_commodity(Shop s) {
		for(int i = 0; i < 10; i++) {
			Commodity c = new Commodity();
			c.setName("commodity_" + i);
			c.setPrice(i + 100);
			c.setShop(s);
			this.commodityManager.save(c);
		}
	}
}
