package com.ziky.bank.repository.json;

import com.ziky.bank.domain.Bank;
import com.ziky.bank.repository.exceptions.JsonDaoException;
import com.ziky.bank.repository.BankDao;
import com.ziky.bank.repository.registry.BankRegistry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Ziky on 8.12.2014.
 */
@Repository("bankDao")
public class BankDaoJson extends JsonDaoImpl<Bank, Integer> implements BankDao {
    private BankRegistry registry;

    public BankDaoJson() throws JsonDaoException {
        super();
        registry = BankRegistry.getInstance();
        readFile();
    }

    @Override
    protected void addToRegistry(Object id, Bank bank) {
        registry.addToRegistry((Integer) id, bank);
    }

    @Override
    protected Bank getFromRegistry(Object id) {
        return registry.getFromRegistry((Integer) id);
    }

    @Override
    protected void deleteFromRegistry(Object id) {
        registry.deleteFromRegistry((Integer) id);
    }

    @Override
    protected void clearRegistry() {
        registry.clearRegistry();
    }

    @Override
    protected List<Bank> getAll() {
        return registry.getAll();
    }

    @Override
    public List<Bank> findAllBanks() {
        return getAll();
    }

    @Override
    protected String getFileName() {
        return "bank.json";
    }

    @Override
    protected Bank extract(Map<String, String> map) {
        Bank bank = new Bank();
        bank.setBankCode(Integer.parseInt(map.get("bankcode")));
        bank.setName(map.get("name"));

        return bank;
    }

    @Override
    protected void bind(Bank entity, Map<String, String> map) {
        map.put("bankcode", entity.getBankCode().toString());
        map.put("name", entity.getName());
    }
}
