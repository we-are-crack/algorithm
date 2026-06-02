select child.id, child.genotype, parent.genotype as parent_genotype
from ecoli_data parent
join ecoli_data child on child.parent_id = parent.id
where (child.genotype & parent.genotype) = parent.genotype
order by child.id