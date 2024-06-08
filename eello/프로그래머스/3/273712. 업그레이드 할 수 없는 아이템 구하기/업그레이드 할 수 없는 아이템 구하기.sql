select
    info.item_id,
    info.item_name,
    info.rarity
from
    item_info info
    left join item_tree tree on info.item_id = tree.parent_item_id
where
    tree.parent_item_id is null
order by
    info.item_id desc