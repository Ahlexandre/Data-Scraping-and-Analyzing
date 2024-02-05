package fr.pantheonsorbonne.ufr27.miashs.poo;

public final class Item {
  private String nom_joueur;
  private Double age;
  private Double point_par_game;
  private Double rebond_par_game;
  private Double assist_par_game;
  private Double steal_par_game;
  private Double block_par_game;
  private String poste;
  private String team;
  private Integer nb_game_jouer;
  private Double nb_minute_jouer_par_game;
  private Integer trois_points;

  public String getNom_joueur() {
    return this.nom_joueur;
  }

  public void setNom_joueur(String nom_joueur) {
    this.nom_joueur = nom_joueur;
  }

  public Double getAge() {
    return this.age;
  }

  public void setAge(Double age) {
    this.age = age;
  }

  public Double getPoint_par_game() {
    return this.point_par_game;
  }

  public void setPoint_par_game(Double point_par_game) {
    this.point_par_game = point_par_game;
  }

  public Double getRebond_par_game() {
    return this.rebond_par_game;
  }

  public void setRebond_par_game(Double rebond_par_game) {
    this.rebond_par_game = rebond_par_game;
  }

  public Double getAssist_par_game() {
    return this.assist_par_game;
  }

  public void setAssist_par_game(Double assist_par_game) {
    this.assist_par_game = assist_par_game;
  }

  public Double getSteal_par_game() {
    return this.steal_par_game;
  }

  public void setSteal_par_game(Double steal_par_game) {
    this.steal_par_game = steal_par_game;
  }

  public Double getBlock_par_game() {
    return this.block_par_game;
  }

  public void setBlock_par_game(Double block_par_game) {
    this.block_par_game = block_par_game;
  }

  public String getPoste() {
    return this.poste;
  }

  public void setPoste(String poste) {
    this.poste = poste;
  }

  public String getTeam() {
    return this.team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public Integer getNb_game_jouer() {
    return this.nb_game_jouer;
  }

  public void setNb_game_jouer(Integer nb_game_jouer) {
    this.nb_game_jouer = nb_game_jouer;
  }

  public Double getNb_minute_jouer_par_game() {
    return this.nb_minute_jouer_par_game;
  }

  public void setNb_minute_jouer_par_game(Double nb_minute_jouer_par_game) {
    this.nb_minute_jouer_par_game = nb_minute_jouer_par_game;
  }

  public Integer getTrois_points() {
    return this.trois_points;
  }

  public void setTrois_points(Integer trois_points) {
    this.trois_points = trois_points;
  }
}
