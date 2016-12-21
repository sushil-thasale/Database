import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Set;

@Entity
@Access(FIELD)
public class Network implements Serializable{
	
	public Network() {
    }
	
    /** Primary key. */
    @Id
    private int id;

    /** Name attribute. */
    @Column(name="capacity", nullable=false)
    private Double capacity;
    
    /** cost attribute. */
    @Column(name="cost",length=200 , nullable=false)
    private String cost;
    
    /*type attribute*/
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Type type;
    
    /** partOf attribute. */
    @ManyToOne(optional=false, cascade=CascadeType.REFRESH)
    @JoinColumn(name="partOf", nullable=false)
    private State partOf;
    
    /** isProvidedBy attribute. */
    @ManyToOne(optional=false, cascade=CascadeType.REFRESH)
    @JoinColumn(name="isProvidedBy", nullable=false)
    private NetworkProvider isProvidedBy;
    
    @Basic
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    
    @Basic
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    
    @Basic
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    
    @Basic
    public State getPartOf() {
        return partOf;
    }
    public void setPartOf(State partOf) {
        this.partOf = partOf;
    }
    
    @Basic
    public NetworkProvider getIsProvidedBy() {
        return isProvidedBy;
    }
    public void SetIsProvidedBy(NetworkProvider isProvidedBy) {
        this.isProvidedBy = isProvidedBy;
    }
}
