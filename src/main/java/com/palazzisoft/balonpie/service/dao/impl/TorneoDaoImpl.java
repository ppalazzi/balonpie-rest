package com.palazzisoft.balonpie.service.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.model.Torneo;

@Repository("torneoDao")
public class TorneoDaoImpl extends AbstractDao implements TorneoDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Torneo> getTorneosByParticipante(Integer participanteId) {
        Query<Torneo> query =
                getSession().createQuery("FROM Torneo t where t.participante.id = :participanteId and t.estado = 1");
        query.setParameter("participanteId", participanteId);

        return query.getResultList();
    }

    @Override
    public boolean isDescripcionAvailable(String descripcion) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Torneo> query = builder.createQuery(Torneo.class);

        Root<Torneo> root = query.from(Torneo.class);
        query.select(root).where(builder.equal(root.get("descripcion"), descripcion));

        return getSession().createQuery(query).uniqueResultOptional().isPresent();
    }

    @Override
    public void saveTorneo(Torneo torneo) {
        this.persist(torneo);
    }
}
