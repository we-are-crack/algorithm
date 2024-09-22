select
    info.item_id,
    info.item_name
from
    item_info info
    join item_tree tree
    on tree.parent_item_id is null
        and info.item_id = tree.item_id
order by
    info.item_id