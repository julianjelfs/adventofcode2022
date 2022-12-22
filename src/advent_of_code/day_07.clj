(ns advent-of-code.day-07
  (:require [clojure.string :as str])
)

(defn process_command [path tree [line & lines]]
  (if-not line
    tree
    (let [[_ ls dir bytes file cd] (re-find #"[$] (ls)|dir (.+)|(\d+) (.+)|[$] cd (.+)" line)]
      (cond 
        ls (process_command path tree lines)
        dir (process_command path (assoc-in tree (conj path dir) {}) lines)
        file (process_command path (assoc-in tree (conj path file) (read-string bytes)) lines)
        cd (case cd 
          "/" (process_command [] tree lines)
          ".." (process_command (pop path) tree lines)
          (process_command (conj path cd) tree lines))))))

(defn parse_tree [input]
  (process_command [] {} input))

(def sizes (atom ()))

(defn dir_size [tree]
  (let [node_size (fn [[k v]]
    (if (int? v)
      v
      (dir_size v)
    ))
    size (reduce + 0 (map node_size tree)) ]
    (swap! sizes conj size)
    size))

(defn part-1
  [input]
   (dir_size (parse_tree (str/split-lines input)))
   (reduce + (filter #(<= % 100000) (deref sizes))))

(defn part-2
  [input]
   (let [used (dir_size (parse_tree (str/split-lines input)))
         unused (- 70000000 used)
         shorfall (- 30000000 unused) ]
    (sort (filter #(>= % shorfall) (deref sizes)))))
