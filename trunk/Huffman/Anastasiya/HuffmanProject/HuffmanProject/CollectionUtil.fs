﻿#light

open System.Collections.Generic


let addToDictionary (d : Dictionary<'a, int>)(key : 'a) = 
    if d.ContainsKey key
        then 
            let old = d.[key]
            let res = d.Remove key
            d.Add (key ,old + 1)
        else
            d.Add (key, 1)
            
            
let addToStage (d : Dictionary< int, (int* 'a list)>)(value : 'a) (stage : int) = 
    if d.ContainsKey stage 
        then 
            let (old_node, old_list) = d.[stage]
            let res = d.Remove stage
            d.Add (stage, (old_node,value :: old_list))
            d
        else
            d.Add (stage,(0, [value]))           
            d
let addNodeToStage (d : Dictionary< int, (int* 'a list)>)(stage : int) = 
    if d.ContainsKey stage 
        then 
            let (old_node, old_list) = d.[stage]
            let res = d.Remove stage
            d.Add (stage, (old_node + 1,old_list))
            d
        else
            d.Add (stage,(1, []))           
            d
            
            
let addCollection (d : Dictionary <'a, 'b>) (collection : seq<'a* 'b>) = 
    Seq.fold (fun (d :Dictionary<'a , 'b>) ((val1, val2) : ('a* 'b)) -> d.Add(val1, val2) 
                                                                        d) d collection            
                                                                        
                                                                        
// Аналог инструкции take. Почему-то я ее не нашла в классе List    
let takeFromList (num : int) (l : 'a list) = 
    Seq.take num l
    |> Seq.fold (fun res el -> el :: res) []       


// Возвращает список без num первых элементов        
let rec removeFirstElements (num : int) (lst : 'a list) = 
    match num, lst with
    | n, l when n <= 0 -> l
    | n, (x :: xs) -> removeFirstElements(n - 1) xs
    | n, [] -> []

                                                                        