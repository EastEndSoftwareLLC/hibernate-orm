/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2012, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.ejb.test.cascade.multilevel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_TOP")
public class Top {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "top")
    List<Middle> middles;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    List<Middle> getMiddles() {
        if (middles == null) {
            middles = new ArrayList<Middle>();
        }
        return middles;
    }

    void setMiddles(List<Middle> middles) {
        this.middles = middles;
    }

    void addMiddle(Middle middle) {
        this.getMiddles().add(middle);
        middle.setTop(this);
    }
}
