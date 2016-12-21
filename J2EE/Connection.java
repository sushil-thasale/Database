import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

@Entity
@Access(FIELD)
public class Connection implements Serializable{
	
	public Connection() {
    }
	
    /** usage attribute. */
    @Column(name="usage", nullable=false)
    private Double usage;

    /** location attribute. */
    @Id
    @ManyToOne(optional=false, cascade={CascadeType.REFRESH,CascadeType.REMOVE},fetch=EAGER)	
    @JoinColumn(name="location", nullable=false)
    private BusinessLocation location;
    
    /** network attribute. */
    @Id
    @ManyToOne(optional=false, cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=EAGER)
    @JoinColumn(name="network", nullable=false)
    private Network network;
    
    public Connection(BusinessLocation location, Network network) {
        this.location = location;
        this.network = network;
    }
    
    @Basic
    public BusinessLocation getLocation() {
        return location;
    }
    public void setLocation(BusinessLocation location) {
        this.location = location;
    }
    
    @Basic
    public Network getNetwork() {
        return network;
    }
    public void setNetwork(Network network) {
        this.network = network;
    }
}

