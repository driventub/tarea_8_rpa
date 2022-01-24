package ec.edu.uce.repository.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import ec.edu.uce.modelo.jpa.Balon;





@Repository
@Transactional
public class BalonRepoImpl implements IBalonRepo{
	
	@PersistenceContext
	private EntityManager e;
	
	
	@Override
	public void insertarBalon(Balon p) {
		this.e.persist(p);
		
	}

	@Override
	public Balon buscarBalonPorId(Integer id) {
		return this.e.find(Balon.class,id);
	}

	@Override
	public void actualizarBalonPorId(Balon p) {
		this.e.merge(p);
		
	}

	@Override
	public void borrarBalonPorId(Integer id) {
		Balon gBorrar = this.buscarBalonPorId(id);
		this.e.remove(gBorrar);
		
	}

	@Override
	public Balon buscarBalonPorMarca(String marca) {
		Query miQuery = this.e.createQuery("select g from Balon g where g.marca=:marca");
		miQuery.setParameter("marca",marca);
		Balon miBalon = (Balon) miQuery.getSingleResult();
		return miBalon;
		
	}

	@Override
	public Balon buscarBalonPorMarcaTyped(String marca) {
		TypedQuery<Balon> myTypedQuery = (TypedQuery<Balon>) this.e.createQuery("select g from Balon g where g.marca=:valor");
		myTypedQuery.setParameter("valor",marca);
		
		return myTypedQuery.getSingleResult();
		
	}

	@Override
	public Balon buscarBalonPorMarcaNamed(String marca) {
		Query miQuery = this.e.createNamedQuery("Balon.buscarPorMarca");
		miQuery.setParameter("valor",marca);
		
		return (Balon) miQuery.getSingleResult();
	}
	
	
}
