package service;

import model.Referral;
import repository.ReferralRepository;

import java.util.List;
import java.util.Optional;

/**
 * Singleton Manager (explicitly demonstrates Singleton pattern for coursework).
 * Responsible ONLY for referral storage operations (cohesive responsibility).
 */
public final class ReferralManager {
    private static ReferralManager instance;
    private final ReferralRepository repo;

    private ReferralManager(ReferralRepository repo) { this.repo = repo; }

    public static synchronized ReferralManager getInstance(ReferralRepository repo) {
        if (instance == null) instance = new ReferralManager(repo);
        return instance;
    }

    public List<Referral> all() { return repo.findAll(); }
    public Optional<Referral> byId(String id) { return repo.findById(id); }
    public void add(Referral r) { repo.add(r); }
    public void update(Referral r) { repo.update(r); }
    public void delete(String id) { repo.deleteById(id); }
}
