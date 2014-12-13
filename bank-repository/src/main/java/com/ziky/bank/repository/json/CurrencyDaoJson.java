package com.ziky.bank.repository.json;

import com.ziky.bank.domain.Currency;
import com.ziky.bank.repository.CurrencyDao;
import com.ziky.bank.repository.registry.CurrencyRegistry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Ziky on 8.12.2014.
 */
@Repository("currencyDao")
public class CurrencyDaoJson extends JsonDaoImpl<Currency, String> implements CurrencyDao {
    private CurrencyRegistry registry = CurrencyRegistry.getInstance();

    public CurrencyDaoJson() {
        super();
    }

    @Override
    protected void addToRegistry(Object id, Currency currency) {
        registry.addToRegistry((String) id, currency);
    }

    @Override
    protected Currency getFromRegistry(Object id) {
        return registry.getFromRegistry((String) id);
    }

    @Override
    protected void deleteFromRegistry(Object id) {
        registry.deleteFromRegistry((String) id);
    }

    @Override
    protected void clearRegistry() {
        registry.clearRegistry();
    }

    @Override
    protected List<Currency> getAll() {
        return registry.getAll();
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return getAll();
    }

    @Override
    protected String getFileName() {
        return "currency.json";
    }

    @Override
    protected Currency extract(Map<String, String> map) {
        Currency currency = new Currency();
        currency.setCode(map.get("code"));
        currency.setName(map.get("name"));

        return currency;
    }

    @Override
    protected void bind(Currency entity, Map<String, String> map) {
        map.put("code", entity.getCode());
        map.put("name", entity.getName());
    }
}
