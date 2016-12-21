import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;

@Entity
@Access(FIELD)
public class Building implements Serializable{
	
	public Building() {
    }
	
    /** Primary key. */
    @Id
    private int id;

    /** Area attribute. */
    @Column(name="address", length=200, nullable=false)
    private String address;

    /** Type association. */
    
    @ManyToOne(optional=false, cascade=CascadeType.REFRESH)
    @JoinColumn(name="partOf", nullable=false)
    private State partOf;    
    
    @Basic
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Basic
    public State getPartOf() {
        return partOf;
    }
    public void setPartOf(State partOf) {
        this.partOf = partOf;
    }
}
